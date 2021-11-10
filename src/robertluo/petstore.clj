(ns robertluo.petstore
  (:require [io.pedestal.http :as http]
            [com.walmartlabs.lacinia.pedestal2 :as lp]
            [com.walmartlabs.lacinia.schema :as schema]
            [robertluo.fun-map :as fm :refer [fnk]]))

(def hello-schema
  (schema/compile
   {:queries
    {:hello
      ;; String is quoted here; in EDN the quotation is not required
     {:type 'String
      :description "A simple greeting."
      :resolve (constantly "world")}}}))

(defn system
  "create system from config"
  [config]
  (fm/life-cycle-map
   #:system
   {:config config
    :schema hello-schema
    :service 
    (fnk [:system/schema :system/config]
         (lp/default-service schema config))
    :server
    (fnk [:system/service]
         (let [http-server
               (-> (http/create-server service)
                   (http/start))]
           (fm/closeable http-server #(http/stop http-server))))}))

(defn start-system []
  (fm/touch (system nil)))

(comment
  (def instance (start-system))
  (fm/halt! instance)
  )

(defn -main
  "The entry-point for 'lein run'"
  [& _]
  (println "\nCreating your server...")
  (start-system))

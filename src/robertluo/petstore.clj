(ns robertluo.petstore
  (:require [io.pedestal.http :as http]
            [com.walmartlabs.lacinia.pedestal2 :as lp]
            [com.walmartlabs.lacinia.schema :as schema]))

(def hello-schema
  (schema/compile
   {:queries
    {:hello
      ;; String is quoted here; in EDN the quotation is not required
     {:type 'String
      :resolve (constantly "world")}}}))

;; Use default options:
(def service (lp/default-service hello-schema nil))

;; This is an adapted service map, that can be started and stopped
;; From the REPL you can call server/start and server/stop on this service
(defonce runnable-service (http/create-server service))

(defn -main
  "The entry-point for 'lein run'"
  [& _]
  (println "\nCreating your server...")
  (http/start runnable-service))

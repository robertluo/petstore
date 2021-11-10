(ns robertluo.schema
  (:require 
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]))

(defn pets
  [_ _ _]
  [])

(defn pet-by-id
  [_ _ _]
  nil)

(defn resolve-map []
  {:query/pets pets
   :query/pet-by-id pet-by-id})

(defn hello-schema []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolve-map))
      schema/compile))

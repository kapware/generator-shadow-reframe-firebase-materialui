(ns <%= name %>.fb.storage
  (:require
   ["firebase" :refer [storage]]
   [oops.core :as oops]))

(defn storage-ref [path]
  (.ref (oops/storage) path))

(defn save! [path file metadata]
  (oops/ocall (storage-ref path) "put" file metadata))

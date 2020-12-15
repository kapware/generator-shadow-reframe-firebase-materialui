(ns <%= name %>.firebase.db
  (:require ["firebase" :refer [database]]
            [oops.core :as oops]))

(defn db-ref [path]
  (.ref (database) path))


(defn save! [path value]
  (oops/ocall (db-ref path) "set" value))


(defn on-added! [path action]
  (oops/ocall (db-ref path) "on" "child_added" action))


(defn on-value! [path action]
  (oops/ocall (db-ref path) "on" "value" action))


(defn off-added! [path]
  (oops/ocall (db-ref path) "off" "child_added"))

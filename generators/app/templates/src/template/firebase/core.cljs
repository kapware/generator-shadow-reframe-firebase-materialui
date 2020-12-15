(ns <%= name %>.firebase.core
  (:require ["firebase" :as firebase]
            [<%= name %>.firebase.auth :as firebase.auth]
            [<%= name %>.config :as config]))


(defn init []
  (firebase/initializeApp (clj->js config/firebase))
  (firebase.auth/on-auth-state-changed!))

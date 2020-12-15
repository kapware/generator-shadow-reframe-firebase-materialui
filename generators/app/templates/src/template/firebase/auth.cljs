(ns <%= name %>.firebase.auth
  (:require ["firebase" :as firebase]
            [oops.core :as oops]
            [re-frame.core :as re-frame]))

(defn sign-in-with-google []
  (let [provider (firebase/auth.GoogleAuthProvider.)]
    (oops/ocall (firebase/auth) "signInWithPopup" provider)))


(defn sign-out []
  (oops/ocall (firebase/auth) "signOut")
  (re-frame/dispatch [:sign-out]))


(defn get-user-details [auth-object]
  (let [name  (oops/oget auth-object "displayName")
        photo (oops/oget auth-object "photoURL")
        email (oops/oget auth-object "email")
        uid   (oops/oget auth-object "uid")]
    {:name  name
     :photo photo
     :email email
     :uid   uid}))


(defn on-auth-state-changed! []
  (oops/ocall
   (firebase/auth)
   "onAuthStateChanged"
   (fn
     [user]
     (if user
       (re-frame/dispatch [:update-user (get-user-details user)])
       (re-frame/dispatch [:set-anonymous-user])))))

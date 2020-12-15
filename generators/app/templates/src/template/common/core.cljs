(ns <%= name %>.common.core
  (:require [re-frame.core :as re-frame]
            [oops.core :as oops]
            [<%= name %>.firebase.db :as firebase.db]))

(def empty-db
  {:window-width (oops/oget js/window "innerWidth") 
   :user {}})


(re-frame/reg-event-db
 :update-user
 (fn
   [db [_ user]]
   (let [user-details {:name  (:name user)
                       :email (:email user)
                       :photo (:photo user)}]
     (firebase.db/save! (str "users/" (:uid user)) (clj->js user-details))
     (assoc db :user user))))


(re-frame/reg-event-db
 :set-anonymous-user
 (fn
   [db _]
   (assoc db :user {})))


(re-frame/reg-event-db
 :initialize
 (fn
   [_ _]
   empty-db))


(re-frame/reg-event-db
 :sign-out
 (fn
   [_ _]
   empty-db))


(re-frame/reg-event-db
 :window-width
 (fn [db [_ window-width]]
   (assoc db :window-width window-width)))


(re-frame/reg-sub
 :user
 (fn
   [db _]
   (:user db)))


(re-frame/reg-sub
 :window-width
 (fn
   [db _]
   (:window-width db)))

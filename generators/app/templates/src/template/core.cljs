(ns <%= name %>.core
  (:require
   [<%= name %>.common.core]
   [reagent.dom :as reagent.dom]
   [re-frame.core :as re-frame]
   [<%= name %>.firebase.core :as firebase]
   ["@material-ui/core/Fade" :default Fade]
   ["@material-ui/core/styles" :refer [MuiThemeProvider createMuiTheme]]
   ["@material-ui/core/List" :default List]
   ["@material-ui/core/colors/cyan" :default cyanColor]
   [<%= name %>.common.top :as top]
   [oops.core :as oops]))

(def theme (createMuiTheme (clj->js {:palette {:primary {:light        (oops/oget cyanColor "400")
                                                         :main         (oops/oget cyanColor "600")
                                                         :dark         (oops/oget cyanColor "900")
                                                         :contrastText "#fff"}}})))

(defn main-component []
  (let []
    (fn []
      [:> Fade {:in true}
       [:div
        [top/bar]]])))


(defn app []
  (let [user (re-frame/subscribe [:user])]
    (fn []
      [:> MuiThemeProvider
       {:theme theme}
       (if @user
         [main-component])])))


(defn ^:dev/afterload mount-root []
  (reagent.dom/render [app]
            (js/document.getElementById "app")))


(defn on-window-resize []
  (re-frame/dispatch [:window-width (oops/oget js/window "innerWidth")]))


(defn ^:export init []
  (re-frame/dispatch-sync [:initialize])
  (firebase/init)
  (mount-root)
  (oops/ocall js/window "addEventListener" "resize" on-window-resize))

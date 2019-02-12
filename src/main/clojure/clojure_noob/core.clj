(ns clojure-noob.core
  (:require [beast-hitting.core :as bs])
  (:require [fwpd.core :as fwpd])
  (:gen-class))

(defn -main [& args]
  (apply bs/-main args))

(defn load-beast-hitting []
  (load-file "modules/beast-hitting/src/main/clojure/beast_hitting/core.clj"))

(defn load-fwpd []
  (load-file "modules/fwpd/src/main/clojure/fwpd/core.clj"))

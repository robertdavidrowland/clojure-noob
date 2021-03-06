(defproject clojure-noob "0.1.0-SNAPSHOT"

  :description "My Clojure Noob Stuff"

  :source-paths ["src/main/clojure"]

  :test-paths ["src/test/clojure"]

  :dependencies [
                 ;; core
                 [org.clojure/clojure "1.9.0"]
                 ]

  :main ^:skip-aot clojure-noob.core

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}}
  )

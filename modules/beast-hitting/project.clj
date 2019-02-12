(defproject beast-hitting "0.1.0-SNAPSHOT"

  :description "Module for hitting beasts"

  :source-paths ["src/main/clojure"]

  :test-paths ["src/test/clojure"]

  :dependencies [
                 ;; core
                 [org.clojure/clojure "1.9.0"]
                 ]

  :main ^:skip-aot beast-hitting.core

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}}
  )

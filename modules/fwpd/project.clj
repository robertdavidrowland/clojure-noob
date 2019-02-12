(defproject fwpd "0.1.0-SNAPSHOT"

  :description "Vampire Data Analysis for FWPD"

  :source-paths ["src/main/clojure"]

  :test-paths ["src/test/clojure"]

  :dependencies [
                 ;; core
                 [org.clojure/clojure "1.9.0"]
                 ]

  :main ^:skip-aot fwpd.core

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}}
  )

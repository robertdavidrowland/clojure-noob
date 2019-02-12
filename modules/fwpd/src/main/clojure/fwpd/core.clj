(ns fwpd.core)
(def filename "/Users/robert/Code/clojure-noob/modules/fwpd/src/main/clojure/fwpd/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name          identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(def validations {:name          string?
                  :glitter-index integer?})

(defn validate
  [vamp-key value]
  ((get validations vamp-key) value))

(defn parse
  "Converts a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))

(def loaded-vamps (mapify (parse (slurp filename))))

(defn validate-vampire
  "Runs validations defined in validate-fn against the vampire"
  [validate-fn vampire]
  (println "validate-vampire")
  (every? true?
          (map (fn [[k v]]
                 (if (validate-fn k v) true false)) vampire)))

(defn append
  [vampires vampire]
  (if (validate-vampire validate vampire)
    (concat vampires (list vampire))
    vampires))

(defn de-mapify
  [vampire]
  (clojure.string/join "," (vals vampire)))

(defn test-de-mapify
  []
  (de-mapify {:name "Rob Rowland", :glitter-index 12}))

(defn de-parse
  [vampires]
  (clojure.string/join "\n"
                       (map de-mapify vampires))
  )

(defn test-de-parse
  []
  (de-parse (append loaded-vamps {:name "Rob Rowland", :glitter-index 12}))
  )

(defn -main
  ([]
   (de-parse (append loaded-vamps {:name "Rob Rowland", :glitter-index 12})))
  ([vampire]
   (de-parse (append loaded-vamps vampire))))

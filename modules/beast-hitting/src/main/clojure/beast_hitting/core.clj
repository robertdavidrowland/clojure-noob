(ns beast-hitting.core
  (:gen-class))

(def hobbit-body-parts [{:name "head" :size 3}
                        {:name "left-eye" :size 1}
                        {:name "left-ear" :size 1}
                        {:name "mouth" :size 1}
                        {:name "nose" :size 1}
                        {:name "neck" :size 2}
                        {:name "left-shoulder" :size 3}
                        {:name "left-upper-arm" :size 3}
                        {:name "chest" :size 10}
                        {:name "back" :size 10}
                        {:name "left-forearm" :size 3}
                        {:name "abdomen" :size 6}
                        {:name "left-kidney" :size 1}
                        {:name "left-hand" :size 2}
                        {:name "left-knee" :size 2}
                        {:name "left-thigh" :size 4}
                        {:name "left-lower-leg" :size 3}
                        {:name "left-achilles" :size 1}
                        {:name "left-foot" :size 2}])

(def beast-body-parts [{:name "head" :size 3}
                       {:name "first-eye" :size 1}
                       {:name "first-ear" :size 1}
                       {:name "mouth" :size 1}
                       {:name "nose" :size 1}
                       {:name "neck" :size 2}
                       {:name "first-shoulder" :size 3}
                       {:name "first-upper-arm" :size 3}
                       {:name "chest" :size 10}
                       {:name "back" :size 10}
                       {:name "first-forearm" :size 3}
                       {:name "abdomen" :size 6}
                       {:name "first-kidney" :size 1}
                       {:name "first-hand" :size 2}
                       {:name "first-knee" :size 2}
                       {:name "first-thigh" :size 4}
                       {:name "first-lower-leg" :size 3}
                       {:name "first-achilles" :size 1}
                       {:name "first-foot" :size 2}])

(def crazy-beast-body-parts [{:name "head" :size 3}
                             {:name "eye-1" :size 1}
                             {:name "ear-1" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "shoulder-1" :size 3}
                             {:name "upper-arm-1" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "forearm-1" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "kidney-1" :size 1}
                             {:name "hand-1" :size 2}
                             {:name "knee-1" :size 2}
                             {:name "thigh-1" :size 4}
                             {:name "lower-leg-1" :size 3}
                             {:name "achilles-1" :size 1}
                             {:name "foot-1" :size 2}])

(defn matching-variable-ped-part
  [part]
  (loop [iteration 2, parts-list #{{:name (:name part) :size (:size part)}}]
    (if (> iteration 100)
      parts-list
      (recur (inc iteration) (conj parts-list {:name (clojure.string/replace (:name part) #"-1$" (str "-" iteration)) :size (:size part)}))))
  )

(defn matching-quinta-ped-part
  [part]
  set [{:name (:name part) :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^first-" "second-") :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^first-" "third-") :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^first-" "fourth-") :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^first-" "fifth-") :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^first-" "second-") :size (:size part)}]
  )

(defn matching-bi-ped-part
  [part]
  set [{:name (:name part) :size (:size part)}
       {:name (clojure.string/replace (:name part) #"^left-" "right-") :size (:size part)}]
  )

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [matching-part asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-part part)))
    []
    asym-body-parts))

(defn hit-byped
  [body-parts]
  (let [sym-parts (symmetrize-body-parts matching-bi-ped-part body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
            accumulated-size (:size part)]
       (if (> accumulated-size target)
         part
         (recur remaining (+ accumulated-size (:size (first remaining))))))))

(defn hit-quint-a-ped
  [body-parts]
  (let [sym-parts (symmetrize-body-parts matching-quinta-ped-part body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(defn hit-crazy-a-ped
  [body-parts]
  (let [sym-parts (symmetrize-body-parts matching-variable-ped-part body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "attack hobbit!")
  (println (hit-byped hobbit-body-parts))
  (println "attack hobbit!")
  (println (hit-byped hobbit-body-parts))
  (println "attack beast!")
  (println (hit-quint-a-ped beast-body-parts))
  (println "attack crazy beast!")
  (println (hit-crazy-a-ped crazy-beast-body-parts))
  "Yahhh!")

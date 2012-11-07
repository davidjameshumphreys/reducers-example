(ns reducers-presentation.average
  (:require
   [clojure.core.reducers :as r]
   [clojure.pprint :as p]
   )
  (:use
   [reducers-presentation.tracing :as t])
  )

(defn areducef [datamap nextvalue]
  (do (t/trace (str "arithmetic reduce " datamap \, nextvalue))
      
      {:total (+ nextvalue (:total datamap)),
       :size  (inc (:size datamap))}))

(defn greducef [datamap nextvalue]
  (do (t/trace (str "geometric reduce " datamap \, nextvalue))

      {:total (* nextvalue (:total datamap)),
       :size  (inc (:size datamap))}))

(defn acombinef
  ([]
     (do (t/trace (str "arithmetic combine []"))
         
         ;; The `identity` value for reduce for the arithmetic mean
         {:total 0, :size 0}))
  
  ([left-map right-map]
     (do (t/trace (str "arithmetic combine [2]" left-map \, right-map))

         ;; When combining the two sub-maps, both totals and sizes are
         ;; added together.
      (merge-with + left-map right-map))))

(defn gcombinef
  ([]
     (do (t/trace (str "geometric combine []"))
         
         {:total 1, :size 0}))
  
  ([left-map right-map]
     (do (t/trace (str "G combine [2]" left-map \, right-map))

         ;; Get the product of the totals from the maps
         {:total (* (:total left-map) (:total right-map)),
          ;;but add the sizes together
          :size  (+ (:size  left-map) (:size  right-map))})
     ))

(defn gmean [coll]
  (t/reset-calls)
  (let [{:keys [total size]}
        (r/fold
         3
         gcombinef
         greducef
         coll
         )]
    
    (Math/pow total (/ 1 size))
    ))

(defn amean [coll]
  (t/reset-calls)
  (let [{:keys [total size]}
        (r/fold
         ;; The size of the chunks
         ;; (you shouldn't need to provide this)
         3
         
         ;; How to combine the sub-results and also the seed value for
         ;; each of the sub-lists
         acombinef
         
         ;; How to reduce the sub-lists (to pass to the sub-results above)
         areducef
         
         coll)]
    (/ total size)))

(defn serial-mean [coll]
  (t/reset-calls)
  (let [{:keys [total size]}
        (reduce
         areducef

         ;; The seed value for the function
         ;;{:total 0,
         ;; :size  0 }
         (acombinef)
         
         coll)
        ]
    (/ total size)))

(ns 
  reducers-presentation.tracing
  (:require [clojure.pprint :as p])
  )

(def calls (atom {}))

(defn reset-calls []
  (reset! calls {}))

(defn show-calls []
  (p/pprint @calls))

;; We will trace all of the function calls and capture the name of the
;; thread.
(defmacro trace [a]
  `(let [t# (. (Thread/currentThread) getName)]
     (swap!
      calls
      #(assoc
           %
         t#
         (conj (get % t# []) ~a))))
  )

(ns untitled1.core)

(defn summ-vecc [vecc] (reduce + vecc))
(defn kek [vecc a b sum]
  (if-not (empty? a)
    (if (> (sum vecc) b)
      (lazy-seq (cons vecc (kek [] a b sum)))
      (recur (conj vecc (first a)) (rest a) b sum))
    (when (> (sum vecc) b) [vecc]))
  )
(kek [] '(1 2 3 4 5 ) 5 summ-vecc)

(ns untitled.core
  (:require [clojure.core.async :refer [>! <! <!! chan go go-loop close!]]))

(def ch2 (chan 10))

(defn veccha [vec]
  (let [c (chan 10)]
    (go (doseq [x vec]
          (>! c x))
        (close! c))
    c))

(defn kek [ch1 ch2 a]
  (go-loop [p 0]
    (if-let [x (<! ch1)]
      (do
        (when (< a (Math/abs (- x p)))
                    (>! ch2 x))
                  (recur x))
        (close! ch2))
    ))

(defn consumer [c]
  (kek (veccha [-5 -10 10 2 5 10 15]) ch2 4)
  (Thread/sleep 1000)
  (go-loop []
    (if-let [value (<! c)]
      (do
        (println "Вывод" value)
        (recur))
      )))

(consumer ch2)

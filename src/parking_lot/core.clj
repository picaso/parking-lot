(ns parking-lot.core 
  (:gen-class))


(def eighty-percent 4/5)

(defn old-enough? [user]
  (>(user :age) 16))

(defn lot-full? [lot]
  (=(count (lot :lot )) (lot :size))
	)

(defn less-than-80-percent? [lot]
  (< 
   (/ (count (lot :lot )) (lot :size))
   eighty-percent)
  )

(defn can-attendant-park? [attendant]
  (or (and (old-enough? attendant) (not (lot-full? (attendant :lot))))
  (and (not (old-enough? attendant)) 
       (less-than-80-percent? (attendant :lot))))
  )

(defn park[attendant car]
  (if (can-attendant-park? attendant) 
    (conj ((attendant :lot) :lot) car)  "Sorry You can't Park")
  )

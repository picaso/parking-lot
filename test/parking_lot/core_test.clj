(ns parking-lot.core-test
  (:use clojure.test
        parking-lot.core))

(def full-lot 
  {:lot '(:1 :2 :3 :4 :5 :6 :7 :8 :9 :10) :size 10})

(def not-full-lot 
  {:lot '(:1 :2 :3 :4 :5 :6 :7 :8 :9) :size 10})



(deftest should-let-above-18-park-in-not-full-lot 
  "Allow an Older guy park in a lot that is not 
  full"
  (def attendant
  {:age 18
   :lot not-full-lot })
  (is (= true (can-attendant-park? attendant))
      )
  )

(deftest should-not-let-above-18-park-in-full-lot 
  "Do not Allow an Older guy park in a lot that is not 
  full"
  (def attendant
  {:age 18
   :lot full-lot })
  (is (= false (can-attendant-park? attendant))
      )
  )

(deftest should-let-below-18-park
  "Allows an attendant who is below 18 park in a lot 
  less than 80%"

(def lot 
  {:lot '(:1 :2 :3 :4 :5 :6) :size 10})

  (def attendant
    {:age 16
     :lot lot})

  (is (= true (can-attendant-park? attendant)))
  )

(deftest should-not-let-below-18-park
  "Do not allow an attendant who is below 18 park in a lot 
   80% full or more"

(def lot 
  {:lot '(:1 :2 :3 :4 :5 :6 :7 :8) :size 10})

  (def attendant
    {:age 16
     :lot lot})

  (is (= false (can-attendant-park? attendant)))
  )

(deftest should-test-car-is-parked-in-available-lot
  "Should allow an attendant park in 
  a given non full lot"

  (def attendant 
    {:age 18
     :lot not-full-lot})
  (is (= [:car :1 :2 :3 :4 :5 :6 :7 :8 :9] (park attendant :car)))
  )

(deftest should-test-car-is-parked-in-full-lot
  "Should not allow an attendant park in 
  a full lot"

  (def attendant 
    {:age 18
     :lot full-lot})
  (is (= :lot-full-can't-park (park attendant :car)))
  )
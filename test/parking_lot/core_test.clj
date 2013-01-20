(ns parking-lot.core-test
  (:use clojure.test
    parking-lot.core))

(def full-lot (->Lot 10 [:1 :2 :3 :4 :5 :6 :7 :8 :9 :10]))

(def not-full-lot(->Lot 10 [:1 :2 :3 :4 :5 :6 :7 :8 :9 ]))

(deftest should-let-above-18-park-in-not-full-lot 
  "Allow an Older guy park in a lot that is not 
  full"
  (def attendant (->Attendant 25 not-full-lot))
  (is (= true (can-attendant-park? attendant))))


(deftest should-not-let-above-18-park-in-full-lot 
  "Do not Allow an Older guy park in a lot that is not 
  full"
  (def attendant (->Attendant 25 full-lot))
  (is (= false (can-attendant-park? attendant))))


(deftest should-let-below-18-park
  "Allows an attendant who is below 18 park in a lot 
  less than 80%"
  (def lot (->Lot 10 [:1 :2 :3 :4 :5 :6]))
  (def attendant (->Attendant 16 lot))
  (is (= true (can-attendant-park? attendant))))


(deftest should-not-let-below-18-park
  "Do not allow an attendant who is below 18 park in a lot 
   80% full or more"
  (def lot (->Lot 10 [:1 :2 :3 :4 :5 :6 :7 :8]))
  (def attendant (->Attendant 16 lot))
  (is (= false (can-attendant-park? attendant))))


(deftest should-test-car-is-parked-in-available-lot
  "Should allow an attendant park in 
  a given non full lot"
  (def attendant (->Attendant 25 not-full-lot))
  (is 
    (= [:1 :2 :3 :4 :5 :6 :7 :8 :9 :car] 
      (-> (park attendant :car) :lot :alot))))


(deftest should-test-car-is-parked-in-full-lot
  "Should not allow an attendant park in 
  a full lot"
  (def attendant (->Attendant 25 full-lot))
  (is (= :lot-full-can't-park (park attendant :car))))


(deftest should-remove-car-from-lot
  "Should remove specified car from a lot"
  (def attendant (->Attendant 25 full-lot))
   (is (= [:1 :2 :3 :4 :5 :6 :7 :8 :10] (-> (remove-car attendant :9) :lot :alot))))


(deftest should-not-remove-non-existent-car-from-lot
  "Should not be able to remove a car that doesn't exist"
  (def attendant (->Attendant 25 full-lot))
   (is (= :no-car-found (remove-car attendant :car))))
  


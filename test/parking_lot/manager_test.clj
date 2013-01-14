(ns parking-lot.manager-test
	 (:use clojure.test
        parking-lot.manager)
  )

(deftest should-test-manager
  "test that manager can manage multiple lots"
  (def lot {:lot [:car :car2 :car3] :size 3})
  (def lot2 {:lot [:car :car2 ] :size 3})
  (def manager {:lots [lot lot2]})

  (is (= lot2 (get-available-parking-lot manager)))
  )

(deftest should-test-manager-gets-no-lot
  "should return nill if no lot is found"
  (def lot {:lot [:car :car2 :car3] :size 3})
  (def lot2 {:lot [:car :car2 "car4"] :size 3})
  (def manager {:lots [lot lot2]})
  
  (is (= nil (get-available-parking-lot manager)))
  )

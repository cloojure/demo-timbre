(ns log-timbre.core-test
  (:require [clojure.test :refer :all]
            [log-timbre.core :refer :all]
            [taoensso.timbre :as timbre] ))

(deftest a-test
  (testing "dummy test"
    (is (= 1 1))

    ; Demonstrate logging with Timbre
    (timbre/info "Dummy test passed.")
    (println     "Dummy test passed.")
  ))

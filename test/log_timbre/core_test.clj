(ns log-timbre.core-test
  (:require [clojure.test :refer :all]
            [log-timbre.core :refer :all]
            [taoensso.timbre :as timbre] ))

(deftest dummy-test
  (testing "dummy test"
    (is (= 1 1))

    ; Demonstrate logging with Timbre
    (timbre/info "Dummy test passed.")
    (println     "Dummy test passed.")
  ))

; Note that when using "lein run", we must place a call to (shutdown-agents) at the end of
; the main program.  If this is omitted there is a one minute delay before (non-daemon)
; agent threads will shutdown.  For some reason, however, this is not required for "lein
; test".  Presumably "lein test" either calls either (shutdown-agents) or (System/exit 0)
; when it is complete.

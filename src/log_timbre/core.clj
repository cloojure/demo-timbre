(ns log-timbre.core
  (:require [clojure.java.io :as io]
            [taoensso.timbre :as timbre] )
  (:gen-class))

; Set up the name of the log output file and delete any contents from previous runs (the
; default is to continually append all runs to the file).
(def log-file-name "log.txt")
(io/delete-file log-file-name :quiet)

(timbre/refer-timbre) ; set up timbre aliases

; The default setup is simple console logging.  We with to turn off console logging and
; turn on file logging to our chosen filename.
(timbre/set-config! [:appenders :standard-out   :enabled?] false)   
(timbre/set-config! [:appenders :spit           :enabled?] true)
(timbre/set-config! [:shared-appender-config :spit-filename] log-file-name)
(timbre/set-config! [:shared-appender-config :spit-filename] log-file-name)

; Set the lowest-level to output as :debug
(timbre/set-level! :debug)

(defn my-fn
  "A simple fn to demonstrate profiling"
  []
  (let [nums (vec (range 1000))]
    (+ (p :fast-sleep (Thread/sleep 1) 10)
       (p :slow-sleep (Thread/sleep 2) 32)
       (p :add  (reduce + nums))
       (p :sub  (reduce - nums))
       (p :mult (reduce * nums))
       (p :div  (reduce / nums)))))

(defn -main []
  (println "Hello, World!")  ; a short message to the console

  ; Demonstrate logging with Timbre
  (trace "Hello, Timbre! trace")  ; will not be logged, below current log-level
  (debug "Hello, Timbre! debug")
  (info  "Hello, Timbre! info")
  (warn  "Hello, Timbre! warn")
  (error "Hello, Timbre! error")
  (fatal "Hello, Timbre! fatal")

  ; Demonstrate 3 arities of spy
  (info "Arg-1")
  (info "Arg-1" :Arg-2)
  (info "Arg-1" :Arg-2 ["Arg-3"] )
  (info "Arg-1" :Arg-2 ["Arg-3"] {:Arg 4} )

  ; Demonstrate 3 arities of spy
  (assert (= {:a 1}     (spy :info "Spy returns the last value" {:a 1} )))
  (assert (= 42         (spy (* 6 7) ))) ; no level implies :debug
  (assert (= 42         (spy :warn (* 6 7))))
  (assert (= {:a 1}     (spy :error "optional message" {:a 1} )))

  ; Even exceptions look nice in the logs
  (error (Exception. "Doh!") "Any extra" :items {:go "here"} )

  ; Demonstrate profiling with Timbre
  (info "(my-fn) => " (my-fn))
  (profile :info :Arithmetic (dotimes [n 100] (my-fn)))

  ; Note that when using "lein run", we must place a call to (shutdown-agents) at the end of
  ; the main program.  If this is omitted there is a one minute delay before (non-daemon)
  ; agent threads will shutdown.  For some reason, however, this is not required for "lein
  ; test".  Presumably "lein test" either calls either (shutdown-agents) or (System/exit 0)
  ; when it is complete.
  (shutdown-agents) 
)

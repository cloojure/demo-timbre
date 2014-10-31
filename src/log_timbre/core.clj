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
  (info "Hello, Timbre! info")
  (spy :info "Hello, Timbre! spy :info")
  (debug "Hello, Timbre! debug")
  (trace "Hello, Timbre! trace")

  ; Even exceptions look nice in the logs
  (info (Exception. "Doh!") "arg2" "arg3")

  ; Demonstrate profiling with Timbre
  (info "(my-fn) => " (my-fn))
  (profile :info :Arithmetic (dotimes [n 100] (my-fn)))

  ; If this is omitted there is a one minute delay before (non-daemon) agent 
  ; threads will shutdown
  (shutdown-agents) 
)

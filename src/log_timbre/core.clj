(ns log-timbre.core
  (:require [clojure.java.io :as io]
            [taoensso.timbre :as timbre] )
  (:gen-class))

(def log-file-name "log.txt")
(io/delete-file log-file-name :quiet)

(timbre/refer-timbre) ; set up timbre aliases
(timbre/set-config! [:appenders :standard-out   :enabled?] false)   ; turn off console logging
(timbre/set-config! [:appenders :spit           :enabled?] true)    ; turn on file logging
(timbre/set-config! [:shared-appender-config :spit-filename] log-file-name) ; output filename

(defn my-fn
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
  (println "Shutting down...")
  (shutdown-agents) 
)

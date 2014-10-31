# log-timbre

A short template on the use of Timbre for Clojure logging

## Usage

Just execute using lein:

    > lein run
    Hello, World!
    Shutting down...

    > cat log.txt
    2014-Oct-30 23:00:11 -0700 localhost.localdomain INFO [log-timbre.core] - Hello, Timbre! info
    2014-Oct-30 23:00:11 -0700 localhost.localdomain INFO [log-timbre.core] - Hello, Timbre! spy :info Hello, Timbre! spy :info
    2014-Oct-30 23:00:11 -0700 localhost.localdomain DEBUG [log-timbre.core] - Hello, Timbre! debug
    2014-Oct-30 23:00:11 -0700 localhost.localdomain INFO [log-timbre.core] - arg2 arg3
    java.lang.Exception: Doh!
             log-timbre.core/-main                          core.clj:   36
        clojure.lang.RestFn.invoke                       RestFn.java:  397
           clojure.lang.Var.invoke                          Var.java:  375
                        user/eval3  form-init6280606650468310964.clj:    1
        clojure.lang.Compiler.eval                     Compiler.java: 6767
        clojure.lang.Compiler.eval                     Compiler.java: 6757
        clojure.lang.Compiler.load                     Compiler.java: 7194
    clojure.lang.Compiler.loadFile                     Compiler.java: 7150
          clojure.main/load-script                          main.clj:  274
             clojure.main/init-opt                          main.clj:  279
           clojure.main/initialize                          main.clj:  307
             clojure.main/null-opt                          main.clj:  342
                 clojure.main/main                          main.clj:  420
        clojure.lang.RestFn.invoke                       RestFn.java:  421
           clojure.lang.Var.invoke                          Var.java:  383
    clojure.lang.AFn.applyToHelper                          AFn.java:  156
          clojure.lang.Var.applyTo                          Var.java:  700
                 clojure.main.main                         main.java:   37

    2014-Oct-30 23:00:12 -0700 localhost.localdomain INFO [log-timbre.core] - (my-fn) =>  42
    2014-Oct-30 23:00:12 -0700 localhost.localdomain INFO [log-timbre.core] - Profiling: :log-timbre.core/Arithmetic
                             Id      nCalls       Min        Max       MAD      Mean   Time% Time
    :log-timbre.core/slow-sleep         100     2.0ms      5.0ms    99.0μs     2.0ms      47 213.0ms
    :log-timbre.core/fast-sleep         100     1.0ms      5.0ms    79.0μs     1.0ms      24 111.0ms
           :log-timbre.core/div         100    68.0μs      1.0ms    85.0μs   234.0μs       5 23.0ms
          :log-timbre.core/mult         100    42.0μs    544.0μs    54.0μs   149.0μs       3 15.0ms
           :log-timbre.core/sub         100    43.0μs    464.0μs    59.0μs   143.0μs       3 14.0ms
           :log-timbre.core/add         100    55.0μs    379.0μs    45.0μs   136.0μs       3 14.0ms
                     Clock Time                                                          100 457.0ms
                 Accounted Time                                                           85 390.0ms


## License

Copyright © 2014 Alan Thompson

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

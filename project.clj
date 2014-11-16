(defproject log-timbre "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [ [org.clojure/clojure "1.7.0-alpha4"]
                  [cooljure  "0.1.15"]
                  [com.taoensso/timbre "3.3.1"] ]
  :main ^:skip-aot log-timbre.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

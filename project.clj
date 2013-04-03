(defproject hello-jogl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [local/jogl-all "2.0.0"]]
  :repositories {"local" ~(str (.toURI (java.io.File. "maven_repo")))}
  :main hello-jogl.core)

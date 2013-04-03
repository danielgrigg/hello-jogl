(defproject hello-jogl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojars.toxi/jogl "2.0.0-rc10"]]
;                 [local/gluegen-rt-natives-macosx-universal "1.0.0"]
;                 [local/gluegen-rt "1.0.0"]
;                 [local/jogl-all "1.0.0"]]
  :repositories {"local" ~(str (.toURI (java.io.File. "maven-repo")))}
  :native-path "lib/jogamp-all-platforms/lib/"
  :main hello-jogl.core)

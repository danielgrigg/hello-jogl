(ns hello-jogl.core
  (:gen-class)
  (:import (java.awt Frame))
  (:import (javax.media.opengl.awt GLCanvas)))

;;(. System setProperty "java.library.path" ".:/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/jav:lib/jogamp-all-platforms/lib/")

(def ^:dynamic canvas (GLCanvas.))
(def ^:dynamic frame (Frame.))

(defn main []
  (do
   nil
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!"))

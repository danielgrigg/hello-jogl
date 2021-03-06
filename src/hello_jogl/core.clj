(ns hello-jogl.core
  (:gen-class)
  (:import (java.awt Frame))
  (:import (java.awt.event WindowAdapter))
  (:import (javax.media.opengl.awt GLCanvas))
  (:import (javax.media.opengl.fixedfunc GLMatrixFunc))
  (:import (javax.media.opengl GL2 GL GLAutoDrawable GLEventListener))
  (:import (com.jogamp.opengl.util Animator)))

(defn java-methods 
  ([java-class re-string-or-pattern]
  (let [re (re-pattern re-string-or-pattern)]
    (for [m (seq (.getMethods java-class))
      :let [m-name (.getName m)]
      :when (re-find re m-name)]
      m-name)))
  ([java-class]
     (java-methods java-class "")))

(defn throw-str [s]
  (throw (Exception. s)))

(def ^:dynamic canvas (GLCanvas.))
(def ^:dynamic frame (Frame.))
(def ^:dynamic animator (Animator. canvas))

(defn jogl-drawble []
  (reify GLAutoDrawable
    (getWidth [this]
      256)))

(def time (atom 0.0))

(defn jogl-listener []
  (reify GLEventListener
    (display [this drawable]
      (when (nil? drawable)
        (throw-str "nil drawable"))
      (do
        (let [gl (.. drawable getGL getGL2)]
          (doto gl
            (.glClearColor 0.3 0.3 0.3 1.0)
              (.glClearDepth 1.0)
              (.glEnable GL/GL_DEPTH_TEST)
              (.glClear GL/GL_DEPTH_BUFFER_BIT)
              (.glClear GL/GL_COLOR_BUFFER_BIT)         
              (.glLoadIdentity)
              (.glTranslatef 0.0 0.0 -5.0)
              (.glRotatef @time 0.0 0.0 1.0)
              (.glBegin GL2/GL_QUADS)
              (.glColor3f 1.0 0.0 0.0)
              (.glVertex3f -1.0 1.0 0.0)
              (.glVertex3f 1.0 1.0 0.0)
              (.glVertex3f 1.0 -1.0 0.0)
              (.glVertex3f -1.0 -1.0 0.0)
              (.glEnd)
         ))
        )
        (swap! time #(+ % 0.2))
)
    (reshape [this drawable x y width height]
      (let [gl (.. drawable getGL getGL2)
            aspect (float (/ width height))
            fh 0.5
            fw (* fh aspect)]
        (doto gl
          (.glMatrixMode GLMatrixFunc/GL_PROJECTION)
          (.glLoadIdentity)
          (.glFrustum (- fw) fw (- fh) fh 1.0 1000.0)
          (.glMatrixMode GLMatrixFunc/GL_MODELVIEW)
          (.glLoadIdentity))))

    (dispose [this drawable])

    (init [this drawable])
    ))


(defn add-window-listener [component]
  (let [listener (proxy [WindowAdapter] []
                   (windowClosing [event]
                    (do
                      (println "windowClosing")
                      (.stop animator)
                      (.dispose frame)
                      (def animator nil)
                      (def canvas nil)
                      (def frame nil))))]
    (.addWindowListener component listener)
    listener))
    
(defn main []
  (do
    (def frame (Frame.))
    (def canvas (GLCanvas.))
    (def animator (Animator. canvas))
    (.addGLEventListener canvas (jogl-listener))
    (doto frame
        (.add canvas)
        (.setSize 640 480)
        (.setResizable true)
        (add-window-listener)
        (.setVisible true))
    (.start animator)
    (.requestFocus canvas)))

;(main)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!"))

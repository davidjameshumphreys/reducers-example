(defproject reducers-example "0.1.0"
  :description "An example of using the new reducers"
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies
  [
   [org.clojure/clojure "1.5.0-alpha7"]
   [org.codehaus.jsr166-mirror/jsr166y "1.7.0"]
   ]
  :repositories
  {"sonatype"
   {:url "http://oss.sonatype.org/content/repositories/releases"
    :snapshots false
    :releases {:checksum :fail :update :always}}
   
   "sonatype-snapshots"
   {:url "http://oss.sonatype.org/content/repositories/snapshots"
    :snapshots true
    :releases {:checksum :fail :update :always}}}
  )

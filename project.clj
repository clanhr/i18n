(defproject clanhr/i18n "1.9.1"
  :description "ClanHR's i18n support"
  :url "https://github.com/clanhr/i18n"
  :dependencies [[org.clojure/clojure "1.8.0-RC2"]
                 [cheshire "5.5.0"]]

   :aliases {"sync"  ["run" "-m" "clanhr.i18n.sync/sync-data"]}
  )

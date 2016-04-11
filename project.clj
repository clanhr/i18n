(defproject clanhr/i18n "1.12.0"
  :description "ClanHR's i18n support"
  :url "https://github.com/clanhr/i18n"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.5.0"]]

   :aliases {"sync"  ["run" "-m" "clanhr.i18n.sync/sync-data"]}
  )

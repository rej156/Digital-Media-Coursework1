(ns digital-media.core
  (:use
   [twitter.oauth]
   [twitter.callbacks]
   [twitter.callbacks.handlers]
   [twitter.api.restful]
   [twitter.api.search]
   [twitter.utils])
  (:import
   (twitter.callbacks.protocols SyncSingleCallback)))

(def my-creds (make-oauth-creds "YH1KhWkh7vQ1vwy5QiUN6ttBS"
                                "bzMjERZ2mkL2mTBV39eG3uYa6X9xMy80bJ7bB63ABmtfwIkdTw"
                                "1654107158-6LpjapKfOhjTUXboQXDMLV9V44nhXTSiNOW33ti"
                                "dX3vMSQFUERyiECRU9KffYyNIqI0qC62RvsIdnvQ3pKDH"))

(def extract-body (SyncSingleCallback. response-return-body
                                       response-throw-error
                                       exception-rethrow))

(defn get-user-id
  "gets the id of the supplied screen name"
  [screen-name]
  (get-in (users-show :oauth-creds my-creds
                      :params {:screen-name screen-name})
                      [:body :id]))

(defn get-current-status-id
  "gets the id of the current status for the supplied screen name"
  [screen-name]
  (let [result (users-show :oauth-creds my-creds
                           :params {:screen-name screen-name})]
    (assert-throw (get-in result [:body :status :id])
                  "could not retrieve the user's profile in
                             'show-user'")))

(def following [
                "@cos_ks",
                "@Barrell12",
                "@mat_gan",
                "@QMCSU",
                "@QMEECS",
                "@shah_hussain20",
                "@Nomi_qmul",
                "@Strider_BZ",
                "@faheempatel",
                "@yahya_qmul",
                "@usman_mak",
                "@AcuityYon",
                "@TheRealistMo",
                "@J_Atsak",
                "@prnav",
                "@adil_cw",
                "@rose_aydin",
                ])

(defn get-retweet-count [screen-name]
  (-> (statuses-show-id :oauth-creds my-creds
                      :callbacks extract-body
                      :params {:id (get-current-status-id screen-name)})
         :retweet_count))

(defn timeline-statuses [screen-name]
  (statuses-user-timeline :oauth-creds my-creds
                          :callbacks extract-body
                          :params {:count 5
                                   :screen_name screen-name}))

(defn prnavs-timeline []
  (statuses-user-timeline :oauth-creds my-creds
                          :callbacks extract-body
                          :params {:count 5
                                   :screen_name "@prnav"}))

;; (map #((juxt :text :retweet_count) %) (map #(timeline-statuses %) following))

(defn indegree [target-screen-name]
  (friendships-show
   :oauth-creds my-creds
   :params {:source-screen-name "@ericjuta"
            :target-screen-name target-screen-name}))

;; (mapv #(-> (indegree %)
;;           :source
;;           (juxt :screen_name :followed_by))
;;      following)

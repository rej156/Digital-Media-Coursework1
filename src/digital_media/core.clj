(ns digital-media.core
  (:use
   [twitter.oauth]
   [twitter.callbacks]
   [twitter.callbacks.handlers]
   [twitter.api.restful]
   [twitter.api.search])
  (:import
   (twitter.callbacks.protocols SyncSingleCallback)))

(def my-creds (make-oauth-creds "YH1KhWkh7vQ1vwy5QiUN6ttBS"
                                "bzMjERZ2mkL2mTBV39eG3uYa6X9xMy80bJ7bB63ABmtfwIkdTw"
                                "1654107158-6LpjapKfOhjTUXboQXDMLV9V44nhXTSiNOW33ti"
                                "dX3vMSQFUERyiECRU9KffYyNIqI0qC62RvsIdnvQ3pKDH"))

(def extract-body (SyncSingleCallback. response-return-body
                                       response-throw-error
                                       exception-rethrow))

(users-show :oauth-creds my-creds
            :callbacks extract-body
            :params {:screen-name "AdamJWynne"})

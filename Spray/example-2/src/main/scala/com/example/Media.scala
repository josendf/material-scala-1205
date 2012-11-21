package com.example

import cc.spray.json._

case class MediaBlob(blobId: String, storageKey: String, uploadType: String, uploadTime: String)

object MediaBlobJsonProtocol extends DefaultJsonProtocol {
  implicit val mediaBlobFormat = jsonFormat4(MediaBlob)
}

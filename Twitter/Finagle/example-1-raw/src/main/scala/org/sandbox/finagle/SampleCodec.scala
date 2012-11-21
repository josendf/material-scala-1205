package org.sandbox.finagle

import com.twitter.finagle.Service
import com.twitter.util.Future
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.{Codec, CodecFactory}
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.string.{StringEncoder, StringDecoder}
import org.jboss.netty.channel.{Channels, ChannelPipelineFactory}
import org.jboss.netty.handler.codec.frame.{ Delimiters
                                           , DelimiterBasedFrameDecoder}
import org.jboss.netty.util.CharsetUtil

/**
 * A really simple demonstration of a custom Codec. This Codec is a newline (\n)
 * delimited line-based protocol. Here we re-use existing encoders/decoders as
 * provided by Netty.
 */
object SampleCodec extends SampleCodec

class SampleCodec extends CodecFactory[String, String] {

  def server = Function.const {

    new Codec[String, String] {

      def pipelineFactory = new ChannelPipelineFactory {

        def getPipeline = {

          val pipeline = Channels.pipeline()

          pipeline.addLast("line"
                          , new DelimiterBasedFrameDecoder(100
                              , Delimiters.lineDelimiter: _*))

          pipeline.addLast("sampleDecoder"
                          , new StringDecoder(CharsetUtil.UTF_8))
          pipeline.addLast("sampleEncoder"
                          , new StringEncoder(CharsetUtil.UTF_8))

          pipeline
        }
      }
    }
  }

  def client = Function.const {

    new Codec[String, String] {

      def pipelineFactory = new ChannelPipelineFactory {

        def getPipeline = {

          val pipeline = Channels.pipeline()

          pipeline.addLast("sampleEncoder"
                          , new StringEncoder(CharsetUtil.UTF_8))

          pipeline.addLast("sampleDecoder"
                          , new StringDecoder(CharsetUtil.UTF_8))

          pipeline
        }
      }
    }
  }
}

package com.tw.login.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tw.login.tools.PackDecoder;
import com.tw.login.tools.PackEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class LoginSocketClient {
	private static final Log logger=LogFactory.getLog(LoginSocketClient.class);
	private static final String IP="127.0.0.1";
	private static final int PORT=8088;
	
	private static final EventLoopGroup group=new NioEventLoopGroup();
	
	protected static void run() throws Exception { 
		Bootstrap bootstrap=new Bootstrap();
		bootstrap.group(group);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline pipeline=ch.pipeline();
				/*
				 *这个地方必须和服务器对应上，否则无法正常解码和编码 
				 * */
				 
				pipeline.addLast("frameDecoder",new ProtobufVarint32FrameDecoder());
				pipeline.addLast("protobufDecoder",new PackDecoder());
				pipeline.addLast("frameEncoder",new ProtobufVarint32LengthFieldPrepender());
				pipeline.addLast("protobufEncoder",new PackEncoder());
				pipeline.addLast(new SocketServerHandler());
				pipeline.addLast(new SocketClientHandler());
			}
			
		});
		Channel cl=bootstrap.connect(IP,PORT).sync().channel();
		cl.writeAndFlush("客户端数据"+"\r\n");
		logger.info("向socket服务器发数据："+"客户端数据"+"\r\n");
	}
	public static void main(String[] args) {
		try {
			run();
		}catch(Exception e) {
			group.shutdownGracefully();
		}
	}
}

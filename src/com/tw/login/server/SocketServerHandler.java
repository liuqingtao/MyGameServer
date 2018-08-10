package com.tw.login.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketServerHandler extends SimpleChannelInboundHandler<String>{
	private static final Log logger=LogFactory.getLog(SocketServerHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object msg) throws Exception {
		String data=(String)msg;
		logger.info("数据内容：data="+data);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
	}

	@Override
	protected void messageReceived(ChannelHandlerContext arg0, String arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}

package com.tw.login.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketClientHandler extends SimpleChannelInboundHandler<String> {
	private static final Log logger=LogFactory.getLog(SocketClientHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object msg) throws Exception {
		// TODO Auto-generated method stub
		String data=msg.toString();
		logger.info("数据内容: data="+data);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void messageReceived(ChannelHandlerContext arg0, String data) throws Exception {
		// TODO Auto-generated method stub
		logger.info("数据内容: data="+data);
	}
	
}

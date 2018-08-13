package com.tw.login.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tw.login.proto.CsLogin;
import com.tw.login.proto.CsLogin.CSLoginInfo;
import com.tw.login.proto.CsLogin.CSLoginReq;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		CSLoginReq.Builder req_builder=CSLoginReq.newBuilder();
		CSLoginInfo.Builder info_builder=CSLoginInfo.newBuilder();
		info_builder.setUserName("liuqingtao");
		info_builder.setPassword("gogogo");
		CSLoginInfo info=info_builder.build();
		req_builder.setLoginInfo(info);
		CSLoginReq req=req_builder.build();
		ctx.writeAndFlush(req);
	}

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

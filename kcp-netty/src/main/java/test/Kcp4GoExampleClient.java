package test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import kcp.ChannelConfig;
import kcp.KcpClient;
import kcp.KcpListener;
import kcp.Ukcp;

import java.net.InetSocketAddress;

/**
 * Created by JinMiao
 * 2019/11/29.
 */
public class KcpGoExampleClient implements KcpListener {

    public static void main(String[] args) {
        ChannelConfig channelConfig = new ChannelConfig();
        channelConfig.nodelay(true,40,2,true);
        channelConfig.setSndwnd(1024);
        channelConfig.setRcvwnd(1024);
        channelConfig.setMtu(1400);
        channelConfig.setFecDataShardCount(10);
        channelConfig.setFecParityShardCount(3);
        channelConfig.setAckNoDelay(false);
        //channelConfig.setTimeoutMillis(10000);
        channelConfig.setCrc32Check(false);

        KcpClient kcpClient = new KcpClient();
        kcpClient.init(Runtime.getRuntime().availableProcessors(),channelConfig);


        KcpGoExampleClient kcpGoExampleClient = new KcpGoExampleClient();
        Ukcp ukcp = kcpClient.connect(new InetSocketAddress("127.0.0.1", 10000), channelConfig, kcpGoExampleClient);
        String msg = "hello!!!!!11111111111111111111111111";
        byte[] bytes = msg.getBytes();
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer(bytes.length);
        byteBuf.writeBytes(bytes);
        ukcp.writeKcpMessage(byteBuf);

    }
    @Override
    public void onConnected(Ukcp ukcp) {

    }

    @Override
    public void handleReceive(ByteBuf byteBuf, Ukcp ukcp, int protocolType) {

    }

    @Override
    public void handleException(Throwable ex, Ukcp ukcp) {

    }

    @Override
    public void handleClose(Ukcp ukcp) {

    }
}
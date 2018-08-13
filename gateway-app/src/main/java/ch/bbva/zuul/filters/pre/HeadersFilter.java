package ch.ggf.zuul.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class HeadersFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		if (req.getHeader(HeaderNames.FACADE_KEY) == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
		}
		if (req.getHeader(HeaderNames.FACADE_USER) == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.I_AM_A_TEAPOT.value());
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}

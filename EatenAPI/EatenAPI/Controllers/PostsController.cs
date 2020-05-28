using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EatenAPI.BLL;
using EatenAPI.Common.Req;
using EatenAPI.Common.Rsp;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EatenAPI.WEB.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        public PostsController()
        {
            _svc = new PostsSvc();
        }

        [HttpPost("get-by-id")]
        public IActionResult getPostById([FromBody]SimpleReq req)
        {
            var res = new SingleRsp();
            res = _svc.Read(req.Id);
            return Ok(res);
        }

        [HttpPost("get-all")]
        public IActionResult getAllPost()
        {
            var res = new SingleRsp();
            res.Data = _svc.All;
            return Ok(res);
        }
        [HttpPost("search-post")]
        public IActionResult SearchPost(string kw)
        {
            var res = new SingleRsp();
            res.Data = _svc.SearchPost(kw);
            return Ok((object)res);
        }

        [HttpPost("add-post")]
        public IActionResult AddPost([FromBody]PostReq req)
        {
            var res = _svc.AddPost(req);
            return Ok(res);
        }

        [HttpPost("get-all-post-info")]
        public IActionResult getAllPostInfo()
        {
            var res = new SingleRsp();
            res.Data = _svc.GetAllPostInfo();
            return Ok(res);
        }

        [HttpPost("create-post")]
        public IActionResult CreatePost([FromBody]PostReq req)
        {
            var res = _svc.CreatePost(req);
            return Ok(res);
        }

        [HttpPost("update-post")]
        public IActionResult UpdatePost([FromBody]PostReq req)
        {
            var res = _svc.UpdatePost(req);
            return Ok(res);
        }

        [HttpPost("delete-post")]
        public IActionResult DeletePost([FromBody]PostReq req)
        {
            var res = _svc.DeletePost(req.PostId);
            return Ok(res);
        }

        private readonly PostsSvc _svc;
    }
}
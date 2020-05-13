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
    public class CommentsController : ControllerBase
    {
        public CommentsController()
        {
            _svc = new CommentsSvc();
        }

        [HttpPost("get-by-id")]
        public IActionResult getCommentById([FromBody]SimpleReq req)
        {
            var res = new SingleRsp();
            res = _svc.Read(req.Id);
            return Ok(res);
        }

        [HttpPost("get-all")]
        public IActionResult getAllComment()
        {
            var res = new SingleRsp();
            res.Data = _svc.All;
            return Ok(res);
        }

        [HttpPost("create-comment")]
        public IActionResult CreateComment([FromBody]CommentReq req)
        {
            var res = _svc.CreateComment(req);
            return Ok(res);
        }

        [HttpPost("update-comment")]
        public IActionResult UpdateComment([FromBody]CommentReq req)
        {
            var res = _svc.UpdateComment(req);
            return Ok(res);
        }

        [HttpPost("delete-comment")]
        public IActionResult DeleteComment([FromBody]CommentReq req)
        {
            var res = _svc.DeleteComment(req.PostId);
            return Ok(res);
        }

        private readonly CommentsSvc _svc;
    }
}
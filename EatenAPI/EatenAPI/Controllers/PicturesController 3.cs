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
    public class PicturesController : ControllerBase
    {
        public PicturesController()
        {
            _svc = new PicturesSvc();
        }

        [HttpPost("get-by-id")]
        public IActionResult getPictureById([FromBody]SimpleReq req)
        {
            var res = new SingleRsp();
            res = _svc.Read(req.Id);
            return Ok(res);
        }

        [HttpPost("get-all")]
        public IActionResult getAllPicture()
        {
            var res = new SingleRsp();
            res.Data = _svc.All;
            return Ok(res);
        }

        [HttpPost("create-picture")]
        public IActionResult CreatePicture([FromBody]PictureReq req)
        {
            var res = _svc.CreatePicture(req);
            return Ok(res);
        }

        [HttpPost("update-picture")]
        public IActionResult UpdatePicture([FromBody]PictureReq req)
        {
            var res = _svc.UpdatePicture(req);
            return Ok(res);
        }

        [HttpPost("delete-picture")]
        public IActionResult DeletePicture([FromBody]PictureReq req)
        {
            var res = _svc.DeletePicture(req.PictureId);
            return Ok(res);
        }

        private readonly PicturesSvc _svc;
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Eaten.BLL;
using Eaten.Common.Req;
using Eaten.Common.Rsp;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Eaten.WEB.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TheLoaiController : Controller
    {
        public TheLoaiController()
        {
            _svc = new TheLoaiSvc();
        }

        [HttpPost("get-by-id")]
        public IActionResult getTheLoaiById([FromBody]SimpleReq req)
        {
            var res = new SingleRsp();

            res = _svc.Read(req.Id);

            return Ok(res);
        }

        [HttpPost("get-all")]
        public IActionResult getAllCategory()
        {
            var res = new SingleRsp();
            res.Data = _svc.All;
            return Ok(res);
        }

        [HttpPost("create-theloai")]
        public IActionResult CreateTheLoai([FromBody]TheLoaiReq req)
        {
            var res = _svc.CreateTheLoai(req);
            return Ok(res);
        }

        [HttpPost("update-theloai")]
        public IActionResult UpdateTheLoai([FromBody]TheLoaiReq req)
        {
            var res = _svc.UpdateTheLoai(req);
            return Ok(res);
        }

        [HttpPost("delete-theloai")]
        public IActionResult DeleteTheLoai([FromBody]TheLoaiReq req)
        {
            var res = _svc.DeleteTheLoai(req.MaLoai);
            return Ok(res);
        }

        private readonly TheLoaiSvc _svc;
    }
}

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
    public class FoodCategoriesController : ControllerBase
    {
        public FoodCategoriesController()
        {
            _svc = new FoodCategoriesSvc();
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

        [HttpPost("create-food-category")]
        public IActionResult CreateFoodCategory([FromBody]FoodCategoryReq req)
        {
            var res = _svc.CreateFoodCategory(req);
            return Ok(res);
        }

        [HttpPost("update-food-category")]
        public IActionResult UpdateFoodCategory([FromBody]FoodCategoryReq req)
        {
            var res = _svc.UpdateFoodCategory(req);
            return Ok(res);
        }

        [HttpPost("delete-food-category")]
        public IActionResult DeleteFoodCategory([FromBody]FoodCategoryReq req)
        {
            var res = _svc.DeleteFoodCategory(req.CategoryId);
            return Ok(res);
        }

        private readonly FoodCategoriesSvc _svc;
    }
}
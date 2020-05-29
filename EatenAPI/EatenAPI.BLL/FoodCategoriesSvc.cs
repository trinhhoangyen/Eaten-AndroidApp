using EatenAPI.Common.BLL;
using EatenAPI.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.BLL
{
    using Common.Req;
    using Common.Rsp;
    using System.Linq;
    public class FoodCategoriesSvc : GenericSvc<FoodCategoriesRep, FoodCategories>
    {
        #region Override
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }
        public override SingleRsp Update(FoodCategories m)
        {
            var res = new SingleRsp();
            var m1 = m.CategoryId > 0 ? _rep.Read(m.CategoryId) : _rep.Read(m.CategoryName);
            if (m1 == null)
                res.SetError("EZ103", "No Data.");
            else
            {
                res = base.Update(m);
                res.Data = m;
            }
            return res;
        }
        #endregion

        #region Methods
        public SingleRsp CreateFoodCategory(FoodCategoryReq cate)
        {
            var res = new SingleRsp();
            var cateNew = new FoodCategories()
            {
                CategoryId = cate.CategoryId,
                CategoryName = cate.CategoryName
            };
            res = _rep.CreateFoodCategory(cateNew);
            return res;
        }
        public SingleRsp UpdateFoodCategory(FoodCategoryReq cate)
        {
            var res = new SingleRsp();
            var cateUpdate = new FoodCategories()
            {
                CategoryName = cate.CategoryName
            };
            res = _rep.UpdateFoodCategory(cateUpdate);
            return res;
        }
        public SingleRsp DeleteFoodCategory(int id)
        {
            var res = new SingleRsp();
            var cate = _rep.All.First(p => p.CategoryId == id);
            res = _rep.DeleteFoodCategory(cate);
            return res;
        }
        #endregion
    }
}

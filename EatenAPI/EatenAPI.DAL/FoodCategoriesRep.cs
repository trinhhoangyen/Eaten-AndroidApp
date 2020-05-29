using EatenAPI.Common.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL
{
    using Common.Rsp;
    using System.Linq;
    public class FoodCategoriesRep : GenericRep<EatenDatabaseContext, FoodCategories>
    {
        #region --Override--
        public override FoodCategories Read(int id)
        {
            var res = All.FirstOrDefault(p => p.CategoryId == id);
            return res;
        }
        #endregion

        #region
        public int Remove(int id)
        {
            var m = All.First(i => i.CategoryId == id);
            m = base.Delete(m);
            return m.CategoryId;
        }
        public SingleRsp CreateFoodCategory(FoodCategories theLoai)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.FoodCategories.Add(theLoai);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        public SingleRsp UpdateFoodCategory(FoodCategories theLoai)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.FoodCategories.Update(theLoai);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        public SingleRsp DeleteFoodCategory(FoodCategories theLoai)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.FoodCategories.Remove(theLoai);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        #endregion
    }
}

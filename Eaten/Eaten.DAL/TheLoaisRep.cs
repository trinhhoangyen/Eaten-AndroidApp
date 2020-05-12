using System.Collections.Generic;
using System.Text;
using System.Linq;
using Eaten.DAL.Models;
using Eaten.Common.DAL;
using Eaten.Common.Rsp;
using System;

namespace Eaten.DAL
{
    public class TheLoaisRep : GenericRep<EatenContext, TheLoai>
    {
        #region --Override--
        public override TheLoai Read(int id)
        {
            var res = All.FirstOrDefault(p => p.MaLoai == id);
            return res;
        }

        public int Remove(int id)
        {
            var m = All.First(i => i.MaLoai == id);
            m = base.Delete(m);
            return m.MaLoai;
        }

        public SingleRsp CreateTheLoai( TheLoai theLoai)
        {
            var res = new SingleRsp();
            using(var context = new EatenContext())
            {
                using( var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.TheLoai.Add(theLoai);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch(Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }    
            }    
            return res;
        }

        public SingleRsp UpdateTheLoai(TheLoai theLoai)
        {
            var res = new SingleRsp();
            using (var context = new EatenContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.TheLoai.Update(theLoai);
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

        public SingleRsp DeleteTheLoai(TheLoai theLoai)
        {
            var res = new SingleRsp();
            using (var context = new EatenContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.TheLoai.Remove(theLoai);
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

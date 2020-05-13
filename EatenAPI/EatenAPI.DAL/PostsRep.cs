using EatenAPI.Common.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL
{
    using Common.Rsp;
    using System.Linq;
    public class PostsRep : GenericRep<EatenDatabaseContext, Posts>
    {
        #region --Override--
        public override Posts Read(int id)
        {
            var res = All.FirstOrDefault(p => p.PostId == id);
            return res;
        }

        public int Remove(int id)
        {
            var m = All.First(i => i.PostId == id);
            m = base.Delete(m);
            return m.PostId;
        }

        public SingleRsp CreatePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Add(post);
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

        public SingleRsp UpdatePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Update(post);
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

        public SingleRsp DeletePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Remove(post);
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

using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Restomation.Models;

namespace Restomation.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SPGetMenuItemsController : ControllerBase
    {
        private readonly testrestaurantContext _context;

        public SPGetMenuItemsController(testrestaurantContext context)
        {
            _context = context;
        }

        // GET: api/SPGetMenuItems
        [HttpGet]
        public List<SPGetMenuItems> GetSPGetMenuItems()
        {
            var result = _context.SPGetMenuItems.FromSqlRaw("exec dbo.getAllMenuItems").ToList();
            return result;
        }

        // GET: api/SPGetMenuItems/5
        [HttpGet("{id}")]
        public List<SPGetMenuItems> GetSPGetMenuItems(int id)
        {
            var parameter = new SqlParameter
            {
                ParameterName = "category",
                Value = id
            };

            var result = _context.SPGetMenuItems.FromSqlRaw("EXEC dbo.getMenuItems @category = "+ id).ToList();
            return result;/*
            var sPGetMenuItems = await _context.SPGetMenuItems.FindAsync(id);

            if (sPGetMenuItems == null)
            {
                return NotFound();
            }

            return sPGetMenuItems;*/
        }

        // PUT: api/SPGetMenuItems/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutSPGetMenuItems(int id, SPGetMenuItems sPGetMenuItems)
        {
            if (id != sPGetMenuItems.ItemId)
            {
                return BadRequest();
            }

            _context.Entry(sPGetMenuItems).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SPGetMenuItemsExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/SPGetMenuItems
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<SPGetMenuItems>> PostSPGetMenuItems(SPGetMenuItems sPGetMenuItems)
        {
            _context.SPGetMenuItems.Add(sPGetMenuItems);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetSPGetMenuItems", new { id = sPGetMenuItems.ItemId }, sPGetMenuItems);
        }

        // DELETE: api/SPGetMenuItems/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<SPGetMenuItems>> DeleteSPGetMenuItems(int id)
        {
            var sPGetMenuItems = await _context.SPGetMenuItems.FindAsync(id);
            if (sPGetMenuItems == null)
            {
                return NotFound();
            }

            _context.SPGetMenuItems.Remove(sPGetMenuItems);
            await _context.SaveChangesAsync();

            return sPGetMenuItems;
        }

        private bool SPGetMenuItemsExists(int id)
        {
            return _context.SPGetMenuItems.Any(e => e.ItemId == id);
        }
    }
}
